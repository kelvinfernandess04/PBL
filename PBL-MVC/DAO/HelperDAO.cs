﻿using System.Data;
using System.Data.SqlClient;

namespace PBL_MVC.DAO
{
    static class HelperDAO
    {
        public static void ExecutaSQL(string sql, SqlParameter[] p)
        {
            SqlConnection conexao = ConexaoBD.GetConexao();
            try
            {
                SqlCommand comando = new SqlCommand(sql, conexao);
                if (p != null)
                    comando.Parameters.AddRange(p);
                comando.ExecuteNonQuery();
            }
            finally
            {
                conexao.Close();
            }
        }

        public static DataTable ExecutaSelect(string sql, SqlParameter[] p)
        {
            SqlConnection cx = ConexaoBD.GetConexao();
            try
            {
                SqlDataAdapter adapter = new SqlDataAdapter(sql, cx);
                if (p != null)
                    adapter.SelectCommand.Parameters.AddRange(p);
                DataTable tabela = new DataTable();
                adapter.Fill(tabela);
                return tabela;
            }
            finally
            {
                cx.Close();
            }
        }

        public static object NullAsDbNull(object valor)
        {
            if (valor == null)
                return DBNull.Value;
            else
                return valor;
        }

        public static int ExecutaProc(string nomeProc, SqlParameter[] parametros, bool consultaUltimoIdentity = false)
        {
            Console.WriteLine("entrou no execc");
            using (SqlConnection conexao = ConexaoBD.GetConexao())
            {
            Console.WriteLine("conexao");
                using (SqlCommand comando = new SqlCommand(nomeProc, conexao))
                {
            Console.WriteLine("comando");
                    comando.CommandType = CommandType.StoredProcedure;
                    if (parametros != null)
                        comando.Parameters.AddRange(parametros);
                    comando.ExecuteNonQuery();
                    if (consultaUltimoIdentity)
                    {

            Console.WriteLine("if");
                        string sql = "select isnull(@@IDENTITY,0)";
                        comando.CommandType = CommandType.Text;
                        comando.CommandText = sql;
                        int pedidoId = Convert.ToInt32(comando.ExecuteScalar());
                        conexao.Close();

            Console.WriteLine("close");
                        return pedidoId;
                    }
                    else
                        return 0;
                }
            }
        }

        public static DataTable ExecutaProcSelectX(string nomeProc, SqlParameter parametro)
        {
            SqlParameter[] p =
            {
                parametro
            };
            return ExecutaProcSelect(nomeProc, p);
        }

        public static DataTable ExecutaProcSelect(string nomeProc, SqlParameter[] parametros)
        {
            try{
            
                using (SqlConnection conexao = ConexaoBD.GetConexao())
                {
                    using (SqlDataAdapter adapter = new SqlDataAdapter(nomeProc, conexao))
                    {
                        if (parametros != null)
                            adapter.SelectCommand.Parameters.AddRange(parametros);

                        adapter.SelectCommand.CommandType = CommandType.StoredProcedure;
                        DataTable tabela = new DataTable();
                        adapter.Fill(tabela);
                        return tabela;
                    }
                }
            }
            catch (Exception ex)
            {
                // Registra a exceção no console ou em outro lugar apropriado
                Console.WriteLine($"Erro ao executar o procedimento armazenado {nomeProc}: {ex.Message}");
                throw; // Lança a exceção para que ela possa ser tratada em um nível superior, se necessário
            }
            // finally{
            //     conexao.Close();
            // }
        }

    }
}

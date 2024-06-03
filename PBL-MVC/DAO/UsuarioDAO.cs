using PBL_MVC.Models;
using System.Data;
using System.Data.SqlClient;

namespace PBL_MVC.DAO
{
    class UsuarioDAO : PadraoDAO<UsuarioViewModel>
    {
        protected override SqlParameter[] CriaParametros(UsuarioViewModel usuario)
        {
            SqlParameter[] p = new SqlParameter[5]; // Tamanho correto do array de parâmetros
            p[0] = new SqlParameter("id", usuario.Id);
            p[1] = new SqlParameter("nome", usuario.Nome);
            p[2] = new SqlParameter("idEmpresa", usuario.IdEmpresa);
            p[3] = new SqlParameter("cargo", usuario.Cargo);
            p[4] = new SqlParameter("senha", usuario.Senha);
            return p;
        }

        protected override UsuarioViewModel MontaModel(DataRow registro)
        {
            UsuarioViewModel u = new UsuarioViewModel();
            u.Id = Convert.ToInt32(registro["id"]);
            u.Nome = registro["nome"].ToString();
            u.IdEmpresa = Convert.ToInt32(registro["idEmpresa"]);
            u.Cargo = registro["cargo"].ToString();
            u.Senha = registro["senha"].ToString(); // Garantir que esta coluna existe
            return u;
        }

        public UsuarioViewModel ValidarUsuario(string nome, string senha)
        {
            var p = new SqlParameter[]
            {
                new SqlParameter("nome", nome),
                new SqlParameter("senha", senha)
            };
            var tabela = HelperDAO.ExecutaProcSelect("spValidaUsuario", p);
            if (tabela.Rows.Count == 0)
                return null;
            else
                return MontaModel(tabela.Rows[0]);
        }

        public void InsertUsuario(UsuarioViewModel usuario)
        {
            var p = new SqlParameter[]
            {
                new SqlParameter("nome", usuario.Nome),
                new SqlParameter("idEmpresa", usuario.IdEmpresa),
                new SqlParameter("cargo", usuario.Cargo),
                new SqlParameter("senha", usuario.Senha)
            };
            HelperDAO.ExecutaProc("spInsert_Usuarios", p);
        }

        protected override void SetTabela()
        {
            Tabela = "Usuarios";
            NomeSpListagem = "spListagemUsuarios";
        }

        
    }
}

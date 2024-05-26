using PBL_MVC.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PBL_MVC.DAO
{
    class UsuarioDAO : PadraoDAO<UsuarioViewModel>
    {
        protected override SqlParameter[] CriaParametros(UsuarioViewModel usuario)
        {
            SqlParameter[] p = new SqlParameter[3];
            p[0] = new SqlParameter("id", usuario.Id);
            p[1] = new SqlParameter("nome", usuario.Nome);
            p[2] = new SqlParameter("idEmpresa", usuario.IdEmpresa);
            p[3] = new SqlParameter("cargo", usuario.Cargo);
            return p;
        }

        protected override UsuarioViewModel MontaModel(DataRow registro)
        {
            UsuarioViewModel u = new UsuarioViewModel();
            u.Id = Convert.ToInt32(registro["id"]);
            u.Nome = registro["nome"].ToString();
            u.IdEmpresa = Convert.ToInt32(registro["idEmpresa"]);
            u.Cargo = registro["cargo"].ToString();

            return u;
        }
        public UsuarioViewModel ValidarUsuario(string username, string password)
        {
            var p = new SqlParameter[]
            {
                new SqlParameter("username", username),
                new SqlParameter("password", password)
            };

            var tabela = HelperDAO.ExecutaProcSelect("spValidarUsuario", p);

            if (tabela.Rows.Count == 0)
                return null;

            return MontaModel(tabela.Rows[0]);
        }


        protected override void SetTabela()
        {
            Tabela = "Usuarios";
            NomeSpListagem = "spListagemUsuarios";
        }
    }
}

namespace PBL_MVC.Models
{
    public class UsuarioViewModel : PadraoViewModel
    {
        public string Nome { get; set; }
        public int IdEmpresa { get; set; }
        public string Cargo { get; set; }
        public string Senha { get; set; }
    }
}

﻿namespace PBL_MVC.Models
{
    public class UsuarioViewModel : PadraoViewModel
    {
        public string Nome { get; set; }
        public int IdEmpresa { get; set; }
        public string Cargo { get; set; }
        public string Senha { get; set; }
        public string Img { get; set; } // Novo campo para a imagem em base64
    }
}

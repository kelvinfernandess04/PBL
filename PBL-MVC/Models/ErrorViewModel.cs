namespace PBL_MVC.Models
{
    public class ErrorViewModel
    {
        public ErrorViewModel(string msgErro)
        {
            Erro = msgErro;
        }

        public ErrorViewModel()
        { }

        public string Erro { get; set; }

        #region In�til 

        public string RequestId { get; set; }

        public bool ShowRequestId => !string.IsNullOrEmpty(RequestId);
        #endregion

    }
}
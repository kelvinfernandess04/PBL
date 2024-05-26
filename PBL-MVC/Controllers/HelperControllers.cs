namespace PBL_MVC.Controllers
{
    public class HelperControllers
    {
        public static Boolean VerificaUserLogado(ISession session)
        {
            string logado = session.GetString("Logado");
            return logado != null && logado == "true";
        }        
    }
}

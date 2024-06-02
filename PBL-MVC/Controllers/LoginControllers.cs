using Microsoft.AspNetCore.Mvc;
using PBL_MVC.DAO;
using PBL_MVC.Models;

namespace PBL_MVC.Controllers
{
    public class LoginController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Index(string username, string password)
        {
            if (ModelState.IsValid)
            {
                UsuarioDAO dao = new UsuarioDAO();
                var user = dao.ValidarUsuario(username, password);

                if (user != null)
                {
                    HttpContext.Session.SetString("Logado", "true");
                    HttpContext.Session.SetString("UserId", user.Id.ToString());
                    HttpContext.Session.SetString("UserName", user.Nome);

                    return RedirectToAction("Index", "Home");
                }
                else
                {
                    ModelState.AddModelError(string.Empty, "Usuário ou senha inválidos.");
                }
            }
            return View();
        }

        public IActionResult Logout()
        {
            HttpContext.Session.Clear();
            return RedirectToAction("Index", "Login");
        }

        public IActionResult Register()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Register(UsuarioViewModel model)
        {
            if (ModelState.IsValid)
            {
                UsuarioDAO dao = new UsuarioDAO();
                dao.InsertUsuario(model);
                return RedirectToAction("Index");
            }
            return View(model);
        }
    }
}

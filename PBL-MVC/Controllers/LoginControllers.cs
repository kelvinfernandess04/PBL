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
            UsuarioDAO dao = new UsuarioDAO();
            UsuarioViewModel usuario = dao.ValidarUsuario(username, password);

            if (usuario != null)
            {
                // Login successful, handle accordingly
                // e.g., set session, redirect, etc.
                return RedirectToAction("Index", "Home");
            }
            else
            {
                // Login failed, show error message
                ViewBag.Error = "Nome de usuário ou senha inválidos.";
                return View();
            }
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

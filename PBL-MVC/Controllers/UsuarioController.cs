using Microsoft.AspNetCore.Mvc;
using PBL_MVC.DAO;
using PBL_MVC.Controllers;
using PBL_MVC.Models;
using System;

public class UsuarioController : PadraoController<UsuarioViewModel>
{
    public override IActionResult Index()
    {
        try
        {
            var lista = DAO.Listagem();
            return View(NomeViewIndex, lista);
        }
        catch (Exception erro)
        {
            return View("Error", new ErrorViewModel(erro.ToString()));
        }
    }
    public UsuarioController()
    {
        DAO = new UsuarioDAO();
        GeraProximoId = true;
        ExigeAutenticacao = true;
        NomeViewIndex = "Index";
        NomeViewForm = "Form";
    }

    // Implementação específica para PreencheDadosParaView, se necessário
    protected override void PreencheDadosParaView(string operacao, UsuarioViewModel model)
    {
        base.PreencheDadosParaView(operacao, model);
    }

    public IActionResult Filter(int? idEmpresa, string nome, string cargo)
    {
        var filteredList = DAO.Listagem()
            .Where(u =>
                (idEmpresa == null || u.IdEmpresa == idEmpresa) &&
                (string.IsNullOrEmpty(nome) || u.Nome.Contains(nome)) &&
                (string.IsNullOrEmpty(cargo) || u.Cargo.Contains(cargo))
            )
            .ToList();

        return PartialView("_UserListPartial", filteredList);
    }


    public override IActionResult Save(UsuarioViewModel model, string operacao)
    {

        try
        {
            if (ModelState.IsValid)
            {
                Console.Write("Valido");
                if (operacao == "I")
                {
                    Console.WriteLine("I");
                    var usuarioDAO = new UsuarioDAO(); // Criar uma instância de UsuarioDAO
                    usuarioDAO.InsertUsuario(model); // Chamar o método InsertUsuario na instância criada
                }
                else if (operacao == "A")
                {

                    Console.WriteLine("A");
                    var usuarioDAO = new UsuarioDAO(); // Criar uma instância de UsuarioDAO
                    usuarioDAO.Update(model);
                }
                return RedirectToAction("Index");
            }
            else
            {
                Console.WriteLine(model.Nome + " " + model.Cargo + " " + model.IdEmpresa + " " + model.Id + " " + model.Senha);
                Console.WriteLine(operacao);
                ViewBag.Operacao = operacao;
                PreencheDadosParaView(operacao, model);
                return View("Form", model);
            }
        }
        catch (Exception erro)
        {
            Console.WriteLine($"Error: {erro.Message}");
            return View("Error", new ErrorViewModel(erro.ToString()));
        }
    }

}

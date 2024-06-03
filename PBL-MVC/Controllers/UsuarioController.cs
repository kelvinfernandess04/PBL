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

        Console.WriteLine("Foi chamado");
        try
        {
            if (ModelState.IsValid)
            {
                Console.Write("Valido");
                if (operacao == "I")
                {

                    Console.WriteLine("I");
                    DAO.Insert(model);
                }
                else if (operacao == "A")
                {
                    Console.WriteLine("A");
                    DAO.Update(model);
                }
                return RedirectToAction("Index");
            }
            else
            {
                Console.WriteLine("invalida");
                ViewBag.Operacao = operacao;
                PreencheDadosParaView(operacao, model);
                return View("Form", model);
            }
        }
        catch (Exception erro)
        {
            return View("Error", new ErrorViewModel(erro.ToString()));
        }
    }
}

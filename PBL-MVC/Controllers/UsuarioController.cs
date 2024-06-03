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

    public override IActionResult Save(UsuarioViewModel model, string operacao)
    {
        try
        {
            if (ModelState.IsValid)
            {
                if (operacao == "I")
                {
                    DAO.Insert(model);
                }
                else if (operacao == "A")
                {
                    DAO.Update(model);
                }
                return RedirectToAction("Index");
            }
            else
            {
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

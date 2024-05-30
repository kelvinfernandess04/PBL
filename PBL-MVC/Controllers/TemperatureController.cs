using Microsoft.AspNetCore.Mvc;
using PBL_MVC.Services;
using System.Threading.Tasks;

namespace PBL_MVC.Controllers
{
    public class TemperatureController : Controller
    {
        private readonly TemperatureService _temperatureService;

        public TemperatureController(TemperatureService temperatureService)
        {
            _temperatureService = temperatureService;
        }

        public async Task<IActionResult> Index()
        {
            var temperatureData = await _temperatureService.GetTemperatureDataAsync();
            return View(temperatureData);
        }
    }
}

using Microsoft.AspNetCore.Mvc;
using PBL_MVC.Services;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
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

        public async Task<IActionResult> Chart()
        {
            var temperatureData = await _temperatureService.GetTemperatureDataAsync();

            using var bitmap = new Bitmap(800, 600);
            using var graphics = Graphics.FromImage(bitmap);
            graphics.Clear(Color.White);

            var pen = new Pen(Color.Black, 2);
            var font = new Font("Arial", 12);

            var maxX = 800 - 40;
            var maxY = 600 - 40;

            var points = new PointF[temperatureData.ContextResponses[0].ContextElement.Attributes[0].Values.Count];

            for (int i = 0; i < points.Length; i++)
            {
                var value = temperatureData.ContextResponses[0].ContextElement.Attributes[0].Values[i];
                points[i] = new PointF(
                    20 + i * (maxX / points.Length),
                    maxY - (float)value.AttrValue * (maxY / 40)
                );
            }

            graphics.DrawLines(pen, points);

            for (int i = 0; i < points.Length; i++)
            {
                graphics.FillEllipse(Brushes.Red, points[i].X - 2, points[i].Y - 2, 4, 4);
                graphics.DrawString(points[i].Y.ToString(), font, Brushes.Black, points[i].X, points[i].Y);
            }

            using var ms = new MemoryStream();
            bitmap.Save(ms, ImageFormat.Png);
            ms.Seek(0, SeekOrigin.Begin);
            return File(ms.ToArray(), "image/png");
        }
    }
}

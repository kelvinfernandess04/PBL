using System.Net.Http;
using System.Threading.Tasks;
using Newtonsoft.Json;
using PBL_MVC.Models;

namespace PBL_MVC.Services
{
    public class TemperatureService
    {
        private readonly HttpClient client;

        public TemperatureService()
        {
            client = new HttpClient();
        }

        public async Task<Root> GetTemperatureDataAsync()
        {
            var request = new HttpRequestMessage(HttpMethod.Get, "http://20.51.218.228:8666/STH/v1/contextEntities/type/Lamp/id/urn:ngsi-ld:Lamp:007/attributes/temperature?lastN=50");
            request.Headers.Add("fiware-service", "smart");
            request.Headers.Add("fiware-servicepath", "/");

            var response = await client.SendAsync(request);
            response.EnsureSuccessStatusCode();

            var jsonResponse = await response.Content.ReadAsStringAsync();
            var temperatureData = JsonConvert.DeserializeObject<Root>(jsonResponse);

            return temperatureData;
        }
    }
}

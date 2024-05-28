using Newtonsoft.Json;
using System.Collections.Generic;

namespace PBL_MVC.Models
{
    public class Attribute
    {
        [JsonProperty("name")]
        public string Name { get; set; }

        [JsonProperty("values")]
        public List<TemperatureValue> Values { get; set; }
    }
}
using Newtonsoft.Json;
using System;

namespace PBL_MVC.Models
{
    public class TemperatureValue:PadraoViewModel
    {

        [JsonProperty("recvTime")]
        public DateTime RecvTime { get; set; }

        [JsonProperty("attrName")]
        public string AttrName { get; set; }

        [JsonProperty("attrType")]
        public string AttrType { get; set; }

        [JsonProperty("attrValue")]
        public double AttrValue { get; set; }
    }
}
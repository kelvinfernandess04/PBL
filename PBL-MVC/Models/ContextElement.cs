using Newtonsoft.Json;

namespace PBL_MVC.Models
{
    public class ContextElement
    {
        [JsonProperty("attributes")]
        public List<Attribute> Attributes { get; set; }
    }
}
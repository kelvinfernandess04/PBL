using Newtonsoft.Json;

namespace PBL_MVC.Models
{
    public class ContextResponse
    {
        [JsonProperty("contextElement")]
        public ContextElement ContextElement { get; set; }
    }
}
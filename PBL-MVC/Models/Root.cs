using Newtonsoft.Json;
using System.Collections.Generic;

namespace PBL_MVC.Models
{
    public class Root
    {
        [JsonProperty("contextResponses")]
        public List<ContextResponse> ContextResponses { get; set; }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Json;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayItem;

namespace CosNet.WebUI.Services
{
    public class CosplayItemService : ICosplayItemService
    {
        private readonly HttpClient _httpClient;

        public CosplayItemService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<IEnumerable<CosplayItemDTO>> GetCosplayItemsAsync(Guid cosplayId)
        {
            return await _httpClient.GetFromJsonAsync<IEnumerable<CosplayItemDTO>>($"cosplay/{cosplayId}/cosplayItem");
        }
    }
}

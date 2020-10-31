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

        public async Task<IEnumerable<CosplayMadeItemDTO>> GetCosplayMadeItemsAsync()
        {
            return await _httpClient.GetFromJsonAsync<IEnumerable<CosplayMadeItemDTO>>("/cosplayItem");
        }
        public async Task<IEnumerable<CosplayBoughtItemDTO>> GetCosplayBoughtItemsAsync()
        {
            return await _httpClient.GetFromJsonAsync<IEnumerable<CosplayBoughtItemDTO>>("/cosplayItem");
        }

    }
}

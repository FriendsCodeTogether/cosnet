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

        public async Task<CosplayItemDTO> GetCosplayItemAsync(Guid cosplayId, Guid cosplayItemId)
        {
            return await _httpClient.GetFromJsonAsync<CosplayItemDTO>($"/cosplay/{cosplayId}/cosplayItem/{cosplayItemId}");
        }

        public async Task CreateCosplayItemAsync(Guid cosplayId, CosplayItemForCreationDTO cosplayItem)
        {
            await _httpClient.PostAsJsonAsync<CosplayItemForCreationDTO>($"/cosplay/{cosplayId}/cosplayItem", cosplayItem);
        }

        public async Task UpdateCosplayItemAsync(Guid cosplayId, Guid cosplayItemId, CosplayItemForUpdateDTO cosplayItem)
        {
            await _httpClient.PutAsJsonAsync<CosplayItemForUpdateDTO>($"/cosplay/{cosplayId}/cosplayItem/{cosplayItemId}", cosplayItem);
        }

        public async Task DeleteCosplayItemAsync(Guid cosplayId, Guid cosplayItemId)
        {
            await _httpClient.DeleteAsync($"/cosplay/{cosplayId}/cosplayItem/{cosplayItemId}");
        }
    }
}

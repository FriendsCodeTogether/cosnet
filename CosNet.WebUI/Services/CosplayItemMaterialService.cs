using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Json;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayItemMaterial;

namespace CosNet.WebUI.Services
{
    public class CosplayItemMaterialService : ICosplayItemMaterialService
    {
        private readonly HttpClient _httpClient;

        public CosplayItemMaterialService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<IEnumerable<CosplayItemMaterialDTO>> GetCosplayItemMaterialsAsync(Guid cosplayItemId)
        {
            return await _httpClient.GetFromJsonAsync<IEnumerable<CosplayItemMaterialDTO>>($"cosplayitem/{cosplayItemId}/cosplayitemmaterial");
        }

        public async Task<CosplayItemMaterialDTO> GetCosplayItemMaterialAsync(Guid cosplayItemId, Guid cosplayItemMaterialId)
        {
            return await _httpClient.GetFromJsonAsync<CosplayItemMaterialDTO>($"/cosplayitem/{cosplayItemId}/cosplayitemmaterial/{cosplayItemMaterialId}");
        }

        public async Task CreateCosplayItemMaterialAsync(Guid cosplayItemId, CosplayItemMaterialForCreationDTO cosplayItemMaterial)
        {
            await _httpClient.PostAsJsonAsync<CosplayItemMaterialForCreationDTO>($"/cosplayitem/{cosplayItemId}/cosplayitemmaterial", cosplayItemMaterial);
        }

        public async Task UpdateCosplayItemMaterialAsync(Guid cosplayItemId, Guid cosplayItemMaterialId, CosplayItemMaterialForUpdateDTO cosplayItemMaterial)
        {
            await _httpClient.PutAsJsonAsync<CosplayItemMaterialForUpdateDTO>($"/cosplayitem/{cosplayItemId}/cosplayitemmaterial/{cosplayItemMaterialId}", cosplayItemMaterial);
        }

        public async Task DeleteCosplayItemMaterialAsync(Guid cosplayItemId, Guid cosplayItemMaterialId)
        {
            await _httpClient.DeleteAsync($"/cosplayitem/{cosplayItemId}/cosplayitemmaterial/{cosplayItemMaterialId}");
        }
    }
}

using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Json;
using System.Threading.Tasks;
using CosNet.Shared.DTOs;
using Microsoft.AspNetCore.Components;

namespace CosNet.WebUI.Services
{
    public class CosplayService : ICosplayService
    {
        private readonly HttpClient _httpClient;

        public CosplayService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<IEnumerable<CosplayDTO>> GetCosplaysAsync()
        {
            return await _httpClient.GetFromJsonAsync<IEnumerable<CosplayDTO>>("/cosplay");
        }

        public async Task<CosplayDTO> GetCosplayAsync(Guid CosplayId)
        {
            return await _httpClient.GetFromJsonAsync<CosplayDTO>($"/cosplay/{CosplayId}");
        }

        public async Task CreateCosplayAsync(CosplayDTO cosplay)
        {
            await _httpClient.PostAsJsonAsync<CosplayDTO>("/cosplay", cosplay);
        }

        public async Task UpdateCosplayAsync(Guid cosplayId, CosplayDTO cosplay)
        {
            await _httpClient.PutAsJsonAsync<CosplayDTO>($"/cosplay/{cosplayId}", cosplay);
        }

        public async Task DeleteCosplayAsync(Guid cosplayId)
        {
            await _httpClient.DeleteAsync($"/cosplay/{cosplayId}");
        }
    }
}

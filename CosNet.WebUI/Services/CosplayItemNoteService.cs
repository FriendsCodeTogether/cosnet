using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Json;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayItemNote;

namespace CosNet.WebUI.Services
{
    public class CosplayItemNoteService : ICosplayItemNoteService
    {
        private readonly HttpClient _httpClient;

        public CosplayItemNoteService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<IEnumerable<CosplayItemNoteDTO>> GetCosplayItemNotesAsync(Guid cosplayItemId)
        {
            return await _httpClient.GetFromJsonAsync<IEnumerable<CosplayItemNoteDTO>>($"cosplayitem/{cosplayItemId}/cosplayitemnote");
        }

        public async Task<CosplayItemNoteDTO> GetCosplayItemNoteAsync(Guid cosplayItemId, Guid cosplayItemNoteId)
        {
            return await _httpClient.GetFromJsonAsync<CosplayItemNoteDTO>($"/cosplayitem/{cosplayItemId}/cosplayitemnote/{cosplayItemNoteId}");
        }

        public async Task CreateCosplayItemNoteAsync(Guid cosplayItemId, CosplayItemNoteForCreationDTO cosplayItemNote)
        {
            await _httpClient.PostAsJsonAsync<CosplayItemNoteForCreationDTO>($"/cosplayitem/{cosplayItemId}/cosplayitemnote", cosplayItemNote);
        }

        public async Task UpdateCosplayItemNoteAsync(Guid cosplayItemId, Guid cosplayItemNoteId, CosplayItemNoteForUpdateDTO cosplayItemNote)
        {
            await _httpClient.PutAsJsonAsync<CosplayItemNoteForUpdateDTO>($"/cosplayitem/{cosplayItemId}/cosplayitemnote/{cosplayItemNoteId}", cosplayItemNote);
        }

        public async Task DeleteCosplayItemNoteAsync(Guid cosplayItemId, Guid cosplayItemNoteId)
        {
            await _httpClient.DeleteAsync($"/cosplayitem/{cosplayItemId}/cosplayitemnote/{cosplayItemNoteId}");
        }
    }
}

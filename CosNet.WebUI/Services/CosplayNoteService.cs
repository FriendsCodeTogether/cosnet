using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Json;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayNote;

namespace CosNet.WebUI.Services
{
    public class CosplayNoteService : ICosplayNoteService
    {
        private readonly HttpClient _httpClient;

        public CosplayNoteService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<IEnumerable<CosplayNoteDTO>> GetCosplayNotesAsync(Guid cosplayId)
        {
            return await _httpClient.GetFromJsonAsync<IEnumerable<CosplayNoteDTO>>($"cosplay/{cosplayId}/cosplaynote");
        }

        public async Task<CosplayNoteDTO> GetCosplayNoteAsync(Guid cosplayId, Guid cosplayNoteId)
        {
            return await _httpClient.GetFromJsonAsync<CosplayNoteDTO>($"/cosplay/{cosplayId}/cosplaynote/{cosplayNoteId}");
        }

        public async Task CreateCosplayNoteAsync(Guid cosplayId, CosplayNoteForCreationDTO cosplayNote)
        {
            await _httpClient.PostAsJsonAsync<CosplayNoteForCreationDTO>($"/cosplay/{cosplayId}/cosplaynote", cosplayNote);
        }

        public async Task UpdateCosplayNoteAsync(Guid cosplayId, Guid cosplayNoteId, CosplayNoteForUpdateDTO cosplayNote)
        {
            await _httpClient.PutAsJsonAsync<CosplayNoteForUpdateDTO>($"/cosplay/{cosplayId}/cosplaynote/{cosplayNoteId}", cosplayNote);
        }

        public async Task DeleteCosplayNoteAsync(Guid cosplayId, Guid cosplayNoteId)
        {
            await _httpClient.DeleteAsync($"/cosplay/{cosplayId}/cosplaynote/{cosplayNoteId}");
        }
    }
}

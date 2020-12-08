using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayItemNote;

namespace CosNet.WebUI.Services
{
    public interface ICosplayItemNoteService
    {
        Task CreateCosplayItemNoteAsync(Guid cosplayItemId, CosplayItemNoteForCreationDTO cosplayItemNote);
        Task DeleteCosplayItemNoteAsync(Guid cosplayItemId, Guid cosplayItemNoteId);
        Task<CosplayItemNoteDTO> GetCosplayItemNoteAsync(Guid cosplayItemId, Guid cosplayItemNoteId);
        Task<IEnumerable<CosplayItemNoteDTO>> GetCosplayItemNotesAsync(Guid cosplayItemId);
        Task UpdateCosplayItemNoteAsync(Guid cosplayItemId, Guid cosplayItemNoteId, CosplayItemNoteForUpdateDTO cosplayItemNote);
    }
}
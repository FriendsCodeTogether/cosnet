using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayNote;

namespace CosNet.WebUI.Services
{
    public interface ICosplayNoteService
    {
        Task CreateCosplayNoteAsync(Guid cosplayId, CosplayNoteForCreationDTO cosplayNote);
        Task DeleteCosplayNoteAsync(Guid cosplayId, Guid cosplayNoteId);
        Task<CosplayNoteDTO> GetCosplayNoteAsync(Guid cosplayId, Guid cosplayNoteId);
        Task<IEnumerable<CosplayNoteDTO>> GetCosplayNotesAsync(Guid cosplayId);
        Task UpdateCosplayNoteAsync(Guid cosplayId, Guid cosplayNoteId, CosplayNoteForUpdateDTO cosplayNote);
    }
}
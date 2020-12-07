using System;
using System.Collections.Generic;
using CosNet.Shared.DTOs.CosplayNote;

namespace CosNet.API.Services
{
    public interface ICosplayNoteService
    {
        void CreateCosplayNote(CosplayNoteForCreationDTO cosplayNote);
        void DeleteCosplayNote(Guid cosplayNoteId);
        CosplayNoteDTO GetCosplayNote(Guid cosplayNoteId);
        IEnumerable<CosplayNoteDTO> GetCosplayNotes();
        void UpdateCosplayNote(Guid cosplayNoteId, CosplayNoteForUpdateDTO cosplayNote);
    }
}
using System;
using System.Collections.Generic;
using CosNet.Shared.DTOs.CosplayItemNote;

namespace CosNet.API.Services
{
    public interface ICosplayItemNoteService
    {
        void CreateCosplayItemNote(CosplayItemNoteForCreationDTO cosplayItemNote);

        void DeleteCosplayItemNote(Guid cosplayItemNoteId);

        CosplayItemNoteDTO GetCosplayItemNote(Guid cosplayItemNoteId);

        IEnumerable<CosplayItemNoteDTO> GetCosplayItemNotes(Guid cosplayId);

        void UpdateCosplayItemNote(Guid cosplayItemNoteId, CosplayItemNoteForUpdateDTO cosplayItemNote);
    }
}

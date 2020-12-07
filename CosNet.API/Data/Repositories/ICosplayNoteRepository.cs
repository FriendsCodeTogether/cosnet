using System;
using System.Collections.Generic;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public interface ICosplayItemNoteRepository
    {
        void AddCosplayItemNote(CosplayItemNote CosplayItemNote);

        bool CosplayItemNoteExists(Guid CosplayItemNoteId);

        void DeleteCosplayItemNote(Guid CosplayItemNoteId);

        CosplayItemNote GetCosplayItemNote(Guid CosplayItemNoteId);

        IEnumerable<CosplayItemNote> GetCosplayItemNotes(Guid cosplayId);

        bool SaveChanges();

        void UpdateCosplayItemNote(CosplayItemNote CosplayItemNote);
    }
}

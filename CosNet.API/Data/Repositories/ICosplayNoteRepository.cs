using System;
using System.Collections.Generic;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public interface ICosplayNoteRepository
    {
        void AddCosplayNote(CosplayNote CosplayNote);
        bool CosplayNoteExists(Guid CosplayNoteId);
        void DeleteCosplayNote(Guid CosplayNoteId);
        CosplayNote GetCosplayNote(Guid CosplayNoteId);
        IEnumerable<CosplayNote> GetCosplayNotes();
        bool SaveChanges();
        void UpdateCosplayNote(CosplayNote CosplayNote);
    }
}
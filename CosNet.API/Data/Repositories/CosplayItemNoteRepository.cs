using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Data.DBContexts;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public class CosplayItemNoteRepository
    {
        private readonly ApplicationDbContext _dbContext;

        public CosplayItemNoteRepository(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IEnumerable<CosplayItemNote> GetCosplayItemNotes(Guid cosplayItemId)
        {
            return _dbContext.CosplayItemNotes.Where(c => c.CosplayItemId == cosplayItemId);
        }

        public CosplayItemNote GetCosplayItemNote(Guid CosplayItemNoteId)
        {
            return _dbContext.CosplayItemNotes.FirstOrDefault(a => a.CosplayItemNoteId == CosplayItemNoteId);
        }

        public void AddCosplayItemNote(CosplayItemNote CosplayItemNote)
        {
            if (CosplayItemNote.CosplayItemNoteId == Guid.Empty)
            {
                CosplayItemNote.CosplayItemNoteId = Guid.NewGuid();
            }
            _dbContext.CosplayItemNotes.Add(CosplayItemNote);
        }

        public void UpdateCosplayItemNote(CosplayItemNote CosplayItemNote)
        {
        }

        public void DeleteCosplayItemNote(Guid CosplayItemNoteId)
        {
            CosplayItemNote CosplayItemNote = GetCosplayItemNote(CosplayItemNoteId);
            _dbContext.CosplayItemNotes.Remove(CosplayItemNote);
        }

        public bool CosplayItemNoteExists(Guid CosplayItemNoteId)
        {
            return _dbContext.CosplayItemNotes.Any(c => c.CosplayItemNoteId == CosplayItemNoteId);
        }

        public bool SaveChanges()
        {
            return (_dbContext.SaveChanges() >= 0);
        }
    }
}

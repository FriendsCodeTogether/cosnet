using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Data.DBContexts;
using CosNet.API.Entities;

namespace CosNet.API.Data.Repositories
{
    public class CosplayNoteRepository
    {
        private readonly ApplicationDbContext _dbContext;

        public CosplayNoteRepository(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IEnumerable<CosplayNote> GetCosplayNotes(Guid cosplayId)
        {
            return _dbContext.CosplayNotes.Where(c => c.CosplayId == cosplayId);
        }

        public CosplayNote GetCosplayNote(Guid CosplayNoteId)
        {
            return _dbContext.CosplayNotes.FirstOrDefault(a => a.CosplayNoteId == CosplayNoteId);
        }

        public void AddCosplayNote(CosplayNote CosplayNote)
        {
            if (CosplayNote.CosplayNoteId == Guid.Empty)
            {
                CosplayNote.CosplayNoteId = Guid.NewGuid();
            }
            _dbContext.CosplayNotes.Add(CosplayNote);
        }

        public void UpdateCosplayNote(CosplayNote CosplayNote)
        {
        }

        public void DeleteCosplayNote(Guid CosplayNoteId)
        {
            CosplayNote CosplayNote = GetCosplayNote(CosplayNoteId);
            _dbContext.CosplayNotes.Remove(CosplayNote);
        }

        public bool CosplayNoteExists(Guid CosplayNoteId)
        {
            return _dbContext.CosplayNotes.Any(c => c.CosplayNoteId == CosplayNoteId);
        }

        public bool SaveChanges()
        {
            return (_dbContext.SaveChanges() >= 0);
        }
    }
}

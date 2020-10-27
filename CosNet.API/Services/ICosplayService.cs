using System;
using System.Collections.Generic;
using CosNet.Shared.DTOs.Cosplay;

namespace CosNet.API.Services
{
    public interface ICosplayService
    {
        void CreateCosplay(CosplayForCreationDTO cosplay);
        void DeleteCosplay(Guid cosplayId);
        CosplayDTO GetCosplay(Guid cosplayId);
        IEnumerable<CosplayDTO> GetCosplays();
        void UpdateCosplay(Guid cosplayId, CosplayForUpdateDTO cosplay);
    }
}

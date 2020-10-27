using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.Cosplay;

namespace CosNet.WebUI.Services
{
    public interface ICosplayService
    {
        Task CreateCosplayAsync(CosplayDTO cosplay);
        Task DeleteCosplayAsync(Guid cosplayId);
        Task<CosplayDTO> GetCosplayAsync(Guid CosplayId);
        Task<IEnumerable<CosplayDTO>> GetCosplaysAsync();
        Task UpdateCosplayAsync(Guid cosplayId, CosplayDTO cosplay);
    }
}

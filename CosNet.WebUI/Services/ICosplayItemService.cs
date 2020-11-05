using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayItem;

namespace CosNet.WebUI.Services
{
    public interface ICosplayItemService
    {
        Task CreateCosplayAsync(Guid cosplayId, CosplayItemDTO cosplayItem);
        Task DeleteCosplayAsync(Guid cosplayId, Guid cosplayItemId);
        Task<CosplayItemDTO> GetCosplayItemAsync(Guid cosplayId, Guid cosplayItemId);
        Task<IEnumerable<CosplayItemDTO>> GetCosplayItemsAsync(Guid cosplayId);
        Task UpdateCosplayAsync(Guid cosplayId, Guid cosplayItemId, CosplayItemDTO cosplayItem);
    }
}
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayItem;

namespace CosNet.WebUI.Services
{
    public interface ICosplayItemService
    {
        Task CreateCosplayItemAsync(Guid cosplayId, CosplayItemDTO cosplayItem);
        Task DeleteCosplayItemAsync(Guid cosplayId, Guid cosplayItemId);
        Task<CosplayItemDTO> GetCosplayItemAsync(Guid cosplayId, Guid cosplayItemId);
        Task<IEnumerable<CosplayItemDTO>> GetCosplayItemsAsync(Guid cosplayId);
        Task UpdateCosplayItemAsync(Guid cosplayId, Guid cosplayItemId, CosplayItemDTO cosplayItem);
    }
}

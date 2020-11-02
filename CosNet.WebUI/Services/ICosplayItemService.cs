using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CosNet.Shared.DTOs.CosplayItem;

namespace CosNet.WebUI.Services
{
    public interface ICosplayItemService
    {
        Task<IEnumerable<CosplayItemDTO>> GetCosplayItemsAsync(Guid cosplayId);
    }
}

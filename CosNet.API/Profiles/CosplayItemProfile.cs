using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.Shared.DTOs.CosplayItem;

namespace CosNet.API.Profiles
{
    public class CosplayItemProfile : Profile
    {
        public CosplayItemProfile()
        {
            CreateMap<CosplayItem, CosplayItemDTO>().ReverseMap();
            CreateMap<CosplayItem, CosplayItemForCreationDTO>().ReverseMap();
            CreateMap<CosplayItem, CosplayItemForUpdateDTO>().ReverseMap();

            CreateMap<CosplayItemBoughtInfo, CosplayItemBoughtInfoDTO>().ReverseMap();
            CreateMap<CosplayItemMadeInfo, CosplayItemMadeInfoDTO>().ReverseMap();
        }
    }
}

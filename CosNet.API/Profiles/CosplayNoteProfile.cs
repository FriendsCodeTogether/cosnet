using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.Shared.DTOs.Cosplay;

namespace CosNet.API.Profiles
{
    public class CosplayNoteProfile : Profile
    {
        public CosplayNoteProfile()
        {
            CreateMap<Cosplay, CosplayDTO>().ReverseMap();
            CreateMap<Cosplay, CosplayForCreationDTO>().ReverseMap();
            CreateMap<Cosplay, CosplayForUpdateDTO>().ReverseMap();
        }
    }
}

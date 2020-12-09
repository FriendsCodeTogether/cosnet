using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.Shared.DTOs.CosplayNote;

namespace CosNet.API.Profiles
{
    public class CosplayNoteProfile : Profile
    {
        public CosplayNoteProfile()
        {
            CreateMap<CosplayNote, CosplayNoteDTO>().ReverseMap();
            CreateMap<CosplayNote, CosplayNoteForCreationDTO>().ReverseMap();
            CreateMap<CosplayNote, CosplayNoteForUpdateDTO>().ReverseMap();
        }
    }
}

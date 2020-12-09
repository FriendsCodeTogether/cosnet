using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using CosNet.API.Entities;
using CosNet.Shared.DTOs.CosplayItemNote;

namespace CosNet.API.Profiles
{
    public class CosplayItemNoteProfile : Profile
    {
        public CosplayItemNoteProfile()
        {
            CreateMap<CosplayItemNote, CosplayItemNoteDTO>().ReverseMap();
            CreateMap<CosplayItemNote, CosplayItemNoteForCreationDTO>().ReverseMap();
            CreateMap<CosplayItemNote, CosplayItemNoteForUpdateDTO>().ReverseMap();
        }
    }
}

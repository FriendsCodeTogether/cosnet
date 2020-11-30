using System;
using System.Security.Claims;

namespace CosNet.API.Services
{
    public interface IUserService
    {
        ClaimsPrincipal User { get; }
        Guid UserId { get; }
    }
}
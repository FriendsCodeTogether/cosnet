using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using IdentityServer4.AccessTokenValidation;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace CosNet.API.StartupSections.Configuration
{
    public static class AuthenticationConfiguration
    {
        public static IServiceCollection AddCosNetAuthentication(this IServiceCollection services, IConfiguration configuration)
        {
            services.AddAuthentication(IdentityServerAuthenticationDefaults.AuthenticationScheme)
               .AddIdentityServerAuthentication(options =>
               {
                   options.Authority = configuration.GetSection("CosNetIDPUrl").Value;
                   options.ApiName = "cosnet-api";
                   options.ApiSecret = Environment.GetEnvironmentVariable("COSNET_API_SECRET");
               });

            return services;
        }
    }
}

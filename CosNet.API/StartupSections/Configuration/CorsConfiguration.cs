using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace CosNet.API.StartupSections.Configuration
{
    public static class CorsConfiguration
    {
        public static IServiceCollection AddCosNetCorsPolicy (this IServiceCollection services, IConfiguration configuration)
        {
            return services.AddCors(options =>
            {
                options.AddPolicy(
                "cosnet-cors-policy",
                builder => builder.WithOrigins(configuration.GetSection("CosNetWebUIUrl").Value)
                   .AllowAnyMethod()
                   .AllowAnyHeader()
                   .AllowCredentials());
            });
        }
    }
}

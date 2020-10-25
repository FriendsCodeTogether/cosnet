using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Services;
using Microsoft.Extensions.DependencyInjection;

namespace CosNet.API.StartupSections.Configuration
{
    public static class ServiceConfiguration
    {
        public static IServiceCollection AddCosNetServices(this IServiceCollection services)
        {
            return services.AddTransient<CosplayService>();
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Data.Repositories;
using Microsoft.Extensions.DependencyInjection;

namespace CosNet.API.StartupSections.Configuration
{
    public static class RepositoryConfiguration
    {
        public static IServiceCollection AddCosNetRepositories(this IServiceCollection services)
        {
            return services.AddTransient<ICosplayRepository, CosplayRepository>();
        }
    }
}

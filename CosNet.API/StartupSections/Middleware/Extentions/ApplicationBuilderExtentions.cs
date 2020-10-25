using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;

namespace CosNet.API.StartupSections.Middleware.Extentions
{
    public static class ApplicationBuilderExtentions
    {
        public static IApplicationBuilder UseExceptionMiddleware(this IApplicationBuilder app)
        {
            return app.UseMiddleware<ExceptionMiddleware>();
        }

        public static IApplicationBuilder UseCosNetSwagger(this IApplicationBuilder app)
        {
            app.UseSwagger();

            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "CosNet API V1");
                c.RoutePrefix = string.Empty;
            });

            return app;
        }

        public static IApplicationBuilder UseCosNetCorsPolicy(this IApplicationBuilder app)
        {
            return app.UseCors("cosnet-cors-policy");
        }
    }
}

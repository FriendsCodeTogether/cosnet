using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

using CosNet.API.DBContexts;

using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace CosNet.API
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var host = CreateHostBuilder(args).Build();
            using (var scope = host.Services.CreateScope())
            {
               var services = scope.ServiceProvider;
               try
               {
                  var context = services.GetRequiredService<ApplicationDbContext>();
                  DbSeeder.RecreateDatabase(context);
                  DbSeeder.Seed(context);
               }
               catch (Exception)
               {
                  //We Could log this in a real world situation
               }
            }

            host.Run();
      }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    webBuilder.UseStartup<Startup>();
                });
    }
}

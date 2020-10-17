using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Entities;
using CosNet.API.Repositories;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace CosNet.API.Controllers
{
   [ApiController]
   [Route("[controller]")]
   public class CosplayController : ControllerBase
   {
      private readonly ICosplayRepository _cosplayRepository;

      public CosplayController(ICosplayRepository cosplayRepository)
      {
         _cosplayRepository = cosplayRepository;
      }

      // GET: <CosplayController>
      [HttpGet]
      public IEnumerable<Cosplay> GetAll()
      {
         return _cosplayRepository.GetAllCosplays();
      }

      // GET <CosplayController>/5
      [HttpGet("{id}")]
      public Cosplay GetCosplay(Guid id)
      {
         Cosplay cosplay = _cosplayRepository.GetCosplayById(id);
         return cosplay;
      }

      // POST <CosplayController>
      [HttpPost]
      public void Post([FromBody] string value)
      {
      }

      // PUT <CosplayController>/5
      [HttpPut("{id}")]
      public void Put(int id, [FromBody] string value)
      {
      }

      // DELETE <CosplayController>/5
      [HttpDelete("{id}")]
      public void Delete(Guid id)
      {
         _cosplayRepository.DeleteCosplay(id);
      }
   }
}

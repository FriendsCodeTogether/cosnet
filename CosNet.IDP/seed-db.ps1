Write-Output "Installing dotnet ef tools."
dotnet tool install -g dotnet-ef --version 5.0.0


Write-Output "Deleting existing CosNet IDP DbContexts:"

Write-Output "Deleting ApplicationDbContext"
dotnet ef database drop -c ApplicationDbContext --force

Write-Output "Deleting PersistedGrantDbContext"
dotnet ef database drop -c PersistedGrantDbContext --force

Write-Output "Deleting ConfigurationDbContext"
dotnet ef database drop -c ConfigurationDbContext --force


Write-Output "Migrating CosNet IDP DbContexts:"

Write-Output "Migrating ApplicationDbContext"
dotnet ef database update -c ApplicationDbContext

Write-Output "Migrating PersistedGrantDbContext"
dotnet ef database update -c PersistedGrantDbContext

Write-Output "Migrating ConfigurationDbContext"
dotnet ef database update -c ConfigurationDbContext


Write-Output "Seeding CosNet IDP DbContexts:"
dotnet run /seed

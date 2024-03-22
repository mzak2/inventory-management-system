package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;


@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        /*Here we begin the instantiation of parts/ products if we do not have any currently
        We create 3 inhouse parts and 2 out of house parts,
        then create the base products. All 3 will have their name, prices, and inventories set.
        finally they are added to the respective repository
         */
        if(partRepository.count() == 0) {
            InhousePart oneTBSSD = new InhousePart();
            oneTBSSD.setName("1TB SSD");
            oneTBSSD.setPrice(210.00);
            oneTBSSD.setInv(14);
            oneTBSSD.setMinInv(0);
            oneTBSSD.setMaxInv(200);

            InhousePart oneTBHDD = new InhousePart();
            oneTBHDD.setName("1TB HDD");
            oneTBHDD.setPrice(80.00);
            oneTBHDD.setInv(51);
            oneTBHDD.setMinInv(0);
            oneTBHDD.setMaxInv(200);

            InhousePart ddr4 = new InhousePart();
            ddr4.setName("DDR4 RAM 8gb");
            ddr4.setPrice(66.00);
            ddr4.setInv(39);
            ddr4.setMinInv(0);
            ddr4.setMaxInv(200);



            partRepository.save(oneTBSSD);
            partRepository.save(oneTBHDD);
            partRepository.save(ddr4);
        }

        if(outsourcedPartRepository.count() == 0){
            OutsourcedPart cpuHeatSink = new OutsourcedPart();
            cpuHeatSink.setName("CPU Heatsink");
            cpuHeatSink.setPrice(25.00);
            cpuHeatSink.setInv(17);
            cpuHeatSink.setCompanyName("MegaCorpa"); //bug fix to error of company name not populating
            cpuHeatSink.setMinInv(0);
            cpuHeatSink.setMaxInv(200);

            OutsourcedPart maxSpeedFan = new OutsourcedPart();
            maxSpeedFan.setName("Crucial MMMAXX Speed Fans");
            maxSpeedFan.setPrice(20.00);
            maxSpeedFan.setInv(110);
            maxSpeedFan.setCompanyName("MegaCorpa"); //bug fix to error of company name not populating
            maxSpeedFan.setMinInv(0);
            maxSpeedFan.setMaxInv(200);

            outsourcedPartRepository.save(cpuHeatSink);
            outsourcedPartRepository.save(maxSpeedFan);
        }

        if(productRepository.count() == 0) {
            Product maxDesktop = new Product("LED RGB MMMAXX Desktop (Oversized)", 3850.99, 3);
            Product gamingLaptop = new Product("Crucial Gaming Laptop (RGB)", 1337.00, 11);
            Product cooledTablet = new Product("Liquid Cooled Tablet", 888.88, 31);
            Product btycPhone = new Product("BTYC Mobile Phone", 999.99, 26);
            Product ssfCorpo = new Product("SFF Corporate PC", 360.99, 156);

            productRepository.save(maxDesktop);
            productRepository.save(gamingLaptop);
            productRepository.save(cooledTablet);
            productRepository.save(btycPhone);
            productRepository.save(ssfCorpo);
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}

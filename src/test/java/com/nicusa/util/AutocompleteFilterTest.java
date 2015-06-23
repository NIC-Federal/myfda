package com.nicusa.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class AutocompleteFilterTest {

  @Test
  public void filterShouldReturnShortList () throws IOException {
    AutocompleteFilter af = new AutocompleteFilter();
    ObjectMapper mapper = new ObjectMapper();
    JsonNode json = mapper.readTree( "[{\"value\":\"ASPIR LOW\"}]" );

    List<String> res = af.filter( json );
    assertEquals( res.size(), 1 );
    assertThat( res.get( 0 ), is( "ASPIR LOW" ));
  }

  @Test
  public void filterKeepsAllSameName () throws IOException {
    AutocompleteFilter af = new AutocompleteFilter();
    ObjectMapper mapper = new ObjectMapper();
    JsonNode json = mapper.readTree(
        "[{\"value\":\"B A\"},{\"value\":\"B B\"},{\"value\":\"B C\"},{\"value\":\"B D\"},{\"value\":\"B E\"},{\"value\":\"B F\"},{\"value\":\"B G\"},{\"value\":\"B H\"},{\"value\":\"B I\"},{\"value\":\"B J\"},{\"value\":\"B K\"},{\"value\":\"B L\"},{\"value\":\"B M\"},{\"value\":\"B N\"},{\"value\":\"B O\"},{\"value\":\"B P\"},{\"value\":\"B Q\"},{\"value\":\"B R\"},{\"value\":\"B S\"},{\"value\":\"B T\"},{\"value\":\"B U\"}]" );

    List<String> res = af.filter( json );
    assertEquals( res.size(), 10 );
    assertThat( res.get( 0 ), is ( "B A" ));
    assertThat( res.get( 1 ), is ( "B B" ));
    assertThat( res.get( 2 ), is ( "B C" ));
    assertThat( res.get( 9 ), is ( "B J" ));
  }

  @Test
  public void filterRemovesItemsLargeDataset () throws IOException {
    AutocompleteFilter af = new AutocompleteFilter();
    ObjectMapper mapper = new ObjectMapper();
    JsonNode json = mapper.readTree( "[{\"value\":\"ASP\"},{\"value\":\"ASPARAGINASE\"},{\"value\":\"ASPARAGINASE [CHEMICAL/INGREDIENT]\"},{\"value\":\"ASPARAGINE\"},{\"value\":\"ASPARAGINE-SPECIFIC ENZYME [ESTABLISHED PHARMACOLOGIC CLASS]\"},{\"value\":\"ASPARAGUS\"},{\"value\":\"ASPARAGUS OFFICINALIS YOUNG SHOOT, MENTHA PIPERITA WHOLE PLANT, MILLEFOLIUM WHOLE PLANT,THYMUS SERPYLLUM WHOLE PLANT, CROCUS SATIVUS DRIED STIGMA, ROSMARINUS OFFICINALIS FLOWERING TWIGS, GLECHOMA HEDERACEA WHOLE PLANT, VERBENA OFFICINALIS WHOLE PLANT, UVA-URSI LEAF, CUPRUM METALLICUM\"},{\"value\":\"ASPARTAME MSG DETOX\"},{\"value\":\"ASPEN\"},{\"value\":\"ASPEN 10 - SANS 110\"},{\"value\":\"ASPEN CHG SPRAYABLE\"},{\"value\":\"ASPEN CHG UDDERWASH\"},{\"value\":\"ASPEN HP 502\"},{\"value\":\"ASPEN HP PRE AND POST\"},{\"value\":\"ASPEN IODINE BARRIER DIP\"},{\"value\":\"ASPEN IODINE BARRIER TEAT DIP\"},{\"value\":\"ASPEN IODINE CONCENTRATE 15X\"},{\"value\":\"ASPEN IODINE CONCENTRATE 3X\"},{\"value\":\"ASPEN IODINE CONCERTRATE 5X\"},{\"value\":\"ASPEN IODINE DIP\"},{\"value\":\"ASPEN IODINE TEAT DIP\"},{\"value\":\"ASPEN POLLEN\"},{\"value\":\"ASPEN PREMIUM DIP\"},{\"value\":\"ASPEN QM GOLD\"},{\"value\":\"ASPEN QM GREEN\"},{\"value\":\"ASPEN QM GREEN BARRIER\"},{\"value\":\"ASPEN QM GREEN1\"},{\"value\":\"ASPEN QM LTGOLD\"},{\"value\":\"ASPEN SUPERIOR\"},{\"value\":\"ASPEN SUPERIOR 25\"},{\"value\":\"ASPEN SUPERIOR BARRIER DIP\"},{\"value\":\"ASPEN UDDERCARE (PART A)\"},{\"value\":\"ASPEN UDDERCARE BARRIER PART A\"},{\"value\":\"ASPEN UDDERCARE PART B\"},{\"value\":\"ASPEN WINTER DIP\"},{\"value\":\"ASPER-FLEX\"},{\"value\":\"ASPER-FLEX CREAM\"},{\"value\":\"ASPERCREME HEAT\"},{\"value\":\"ASPERCREME MAX\"},{\"value\":\"ASPERCREME MAX NO MESS ROLL ON\"},{\"value\":\"ASPERCREME PAIN RELIEVING\"},{\"value\":\"ASPERCREME WITH LIDOCAINE PAIN RELIEVING CREME\"},{\"value\":\"ASPERGILLUS\"},{\"value\":\"ASPERGILLUS FLAVUS\"},{\"value\":\"ASPERGILLUS FLAVUS VAR. ORYZAE\"},{\"value\":\"ASPERGILLUS FUMIGATUS\"},{\"value\":\"ASPERGILLUS FUMIGATUS AND BOTRYTIS CINEREA AND CHAETOMIUM GLOBOSUM AND EPICOCCUM NIGRUM AND FUSARIUM OXYSPORUM VASINFECTUM AND COCHLIOBOLUS SATIVUS AND NEUROSPORA SITOPHILA AND MUCOR PLUMBEUS AND PHOMA EXIGUA VAR. EXIGUA AND PENICILLIUM CHRYSOGENUM VAR. CHRYSOGENUM AND AUREOBASIDIUM PULLULANS VAR. PULLUTANS AND RHIZOPUS STOLONIFER AND RHODOTORULA MUCILAGINOSA AND SACCHAROMYCES CEREVISIAE AND GEOTRICHUM CANDIDUM\"},{\"value\":\"ASPERGILLUS GLAUCUS\"},{\"value\":\"ASPERGILLUS MIXTURE\"},{\"value\":\"ASPERGILLUS NIGER\"}]" );
    
    List<String> res = af.filter( json );
    assertEquals( res.size(), 10 );
    assertThat( res.get( 1 ), is( "ASPARAGINASE" ));
    assertThat( res.get( 2 ), is( "ASPARAGINE" ));
  }
}

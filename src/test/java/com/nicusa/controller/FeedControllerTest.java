package com.nicusa.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nicusa.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FeedControllerTest extends MockMvcTestBase
{


    @Test
    public void testGetFDARecalls() throws Exception
    {
        MvcResult result = mockMvc.perform(get("/recalls")).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testGetDrugRecalls() throws Exception
    {
      MvcResult result = mockMvc.perform(get("/drug/recalls")).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testGetDrugRecallsWithLimit() throws Exception
    {
      MvcResult result = mockMvc.perform(get("/drug/recalls?limit=5"))
              .andExpect(status().isOk()).andReturn();
      JsonNode node =  objectMapper.readTree(result.getResponse().getContentAsString());
      assertNotNull(node);
      assertTrue(node.get("results").isArray());
      assertTrue(5 == node.get("results").size());
    }
    @Test
    public void testGetDrugRecallsWithLimitAndSkip() throws Exception
    {
      MvcResult result = mockMvc.perform(get("/drug/recalls?limit=10&skip=5"))
        .andExpect(status().isOk()).andReturn();
      JsonNode node =  objectMapper.readTree(result.getResponse().getContentAsString());
      assertNotNull(node);
      assertTrue(node.get("results").isArray());
      assertTrue(10 == node.get("results").size());
      assertTrue(5 == node.get("meta").get("results").get("skip").intValue());
    }

    @Test
    public void testGetDrugRecallsWithFromDtAndToDt() throws Exception
    {
      MvcResult result = mockMvc.perform(get("/drug/recalls?fromDt=20150101&toDt=20150301"))
        .andExpect(status().isOk()).andReturn();
      JsonNode node =  objectMapper.readTree(result.getResponse().getContentAsString());
      assertNotNull(node);
      assertTrue(node.get("results").isArray());
      assertTrue(5 == node.get("results").size());
    }

    @Test
    public void testGetDrugRecallsForUnii() throws Exception
    {
        MvcResult result = mockMvc.perform(get("/drug/enforcements?unii=T2410KM04A"))
                .andExpect(status().isOk()).andReturn();
        JsonNode node =  objectMapper.readTree(result.getResponse().getContentAsString());
        assertNotNull(node);
        assertTrue(node.get("results").isArray());
        assertTrue(node.get("results").size() == node.get("meta").get("results").get("total").asInt());
    }

}

package io.github.zhanggang.router;

import java.util.*;

// Weight
// - server01,20
// - server02,30
// - server03,50
public class WeightHttpEndpointRouter implements HttpEndpointRouter {

    private Map<String, Integer> serverWeightMap = new HashMap<>();

    public WeightHttpEndpointRouter() {
        serverWeightMap.put("http://localhost:8801", 2);
        serverWeightMap.put("http://localhost:8802", 3);
        serverWeightMap.put("http://localhost:8803", 5);
    }

    @Override
    public String route(List<String> endpoints) {
        Integer totalWeight = 0;
        List<String> servers = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : serverWeightMap.entrySet()) {
            Integer weight = entry.getValue();
            totalWeight += weight;
            for (Integer i = 0; i < weight; i++) {
                servers.add(entry.getKey());
            }
        }
        Random random = new Random(System.currentTimeMillis());
        int idx = random.nextInt(totalWeight);
        return servers.get(idx);
    }


//    @Override
//    public String route(List<String> endpoints) {
//        Integer totalWeight = 0;
//        for (Map.Entry<String, Integer> stringIntegerEntry : serverWeightMap.entrySet()) {
//            totalWeight += stringIntegerEntry.getValue();
//        }
//        Random random = new Random(System.currentTimeMillis());
//        int offset = random.nextInt(totalWeight);
//        for (String endpoint : endpoints) {
//            int weight = serverWeightMap.get(endpoint);
//            offset = offset - weight;
//            if (offset < 0) {
//                return endpoint;
//            }
//        }
//        return endpoints.get(random.nextInt(endpoints.size()));
//    }
}
